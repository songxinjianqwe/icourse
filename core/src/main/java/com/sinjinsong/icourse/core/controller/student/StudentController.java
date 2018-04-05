package com.sinjinsong.icourse.core.controller.student;

import com.github.pagehelper.PageInfo;
import com.sinjinsong.icourse.common.exception.RestValidationException;
import com.sinjinsong.icourse.common.properties.AuthenticationProperties;
import com.sinjinsong.icourse.common.properties.PageProperties;
import com.sinjinsong.icourse.common.security.verification.VerificationManager;
import com.sinjinsong.icourse.common.util.UUIDUtil;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;
import com.sinjinsong.icourse.core.enumeration.student.StudentStatus;
import com.sinjinsong.icourse.core.exception.security.ActivationCodeValidationException;
import com.sinjinsong.icourse.core.exception.user.PasswordNotFoundException;
import com.sinjinsong.icourse.core.exception.user.UserNotFoundException;
import com.sinjinsong.icourse.core.exception.user.UsernameExistedException;
import com.sinjinsong.icourse.core.service.email.EmailService;
import com.sinjinsong.icourse.core.service.student.StudentService;
import com.sinjinsong.icourse.core.service.user.UserQueryService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private VerificationManager verificationManager;
    @Autowired
    private AuthenticationProperties authenticationProperties;
    @Autowired
    private EmailService emailService;

    @GetMapping("/query/{key}")
    @PostAuthorize("(hasRole('MANAGER')) or (returnObject.username ==  principal.username)")
    @ApiOperation(value = "按某属性查询用户", notes = "属性可以是id或username或email或手机号", response = StudentDO.class, authorizations = {@Authorization("登录权限")})
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "未登录"),
            @ApiResponse(code = 404, message = "查询模式未找到"),
            @ApiResponse(code = 403, message = "只有管理员或用户自己能查询自己的用户信息"),
    })
    public StudentDO findByKey(@PathVariable("key") @ApiParam(value = "查询关键字", required = true) String key, @RequestParam("mode") @ApiParam(value = "查询模式，可以是id或username或email", required = true) String mode) {
        if (mode.equalsIgnoreCase("id")) {
            return studentService.findById(Long.valueOf(key));
        } else if (mode.equalsIgnoreCase("username")) {
            return studentService.findByUsername(key);
        } else if (mode.equalsIgnoreCase("email")) {
            return studentService.findByEmail(key);
        } else {
            return null;
        }
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "创建用户", response = StudentDO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "用户名已存在"),
            @ApiResponse(code = 400, message = "用户属性校验失败")
    })
    public StudentDO createStudent(@RequestBody @Valid @ApiParam(value = "用户信息，用户的用户名、密码、昵称、邮箱不可为空", required = true) StudentDO student, BindingResult result) {
        log.info("{}", student);
        if (userQueryService.exists(student.getUsername())) {
            throw new UsernameExistedException(student.getUsername());
        } else if (result.hasErrors()) {
            throw new RestValidationException(result.getFieldErrors());
        } else if (student.getPassword() == null) {
            throw new PasswordNotFoundException();
        }
        studentService.save(student);

        String activationCode = UUIDUtil.uuid();
        //发送验证邮件
        verificationManager.createVerificationCode(activationCode, String.valueOf(student.getId()), authenticationProperties.getActivationCodeExpireTime());
        log.info("{}     {}", student.getEmail(), student.getId());
        Map<String, Object> params = new HashMap<>();
        params.put("id", student.getId());
        params.put("activationCode", activationCode);
        emailService.sendHTML(student.getEmail(), "activation", params, null);
        return student;
    }

    @PostMapping("/{id}/activation")
    @ApiOperation(value = "用户激活，前置条件是用户已注册且在24小时内", response = StudentDO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "未注册或超时或激活码错误")
    })
    public StudentDO activate(@PathVariable("id") @ApiParam(value = "用户id", required = true) Long id, @RequestParam("activationCode") @ApiParam(value = "激活码", required = true) String activationCode) {
        StudentDO student = studentService.findById(id);
        if (student == null) {
            throw new UserNotFoundException(String.valueOf(id));
        }
        //获取Redis中的验证码
        if (!verificationManager.checkVerificationCode(activationCode, String.valueOf(id))) {
            verificationManager.deleteVerificationCode(activationCode);
            throw new ActivationCodeValidationException(activationCode);
        }
        student.setStatus(StudentStatus.ACTIVATED);
        verificationManager.deleteVerificationCode(activationCode);
        studentService.update(student);
        return student;
    }

    @PutMapping
    @PreAuthorize("#student.id == principal.id or hasRole('MANAGER')")
    @ApiOperation(value = "更新用户信息", authorizations = {@Authorization("登录权限")})
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "未登录"),
            @ApiResponse(code = 404, message = "用户属性校验失败"),
            @ApiResponse(code = 403, message = "只有管理员或用户自己能更新用户信息"),

    })
    public void updateUser(@RequestBody @Valid @ApiParam(value = "用户信息，用户的用户名、密码、昵称、邮箱不可为空", required = true) StudentDO student, BindingResult result) {
        if (result.hasErrors()) {
            throw new RestValidationException(result.getFieldErrors());
        }

        studentService.update(student);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "分页查询用户信息", response = PageInfo.class, authorizations = {@Authorization("登录权限")})
    @ApiResponses(value = {@ApiResponse(code = 401, message = "未登录")})
    public PageInfo<StudentDO> findAllUsers(@RequestParam(value = "pageNum", required = false, defaultValue = PageProperties.DEFAULT_PAGE_NUM) @ApiParam(value = "页码，从1开始", defaultValue = PageProperties.DEFAULT_PAGE_NUM) Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = PageProperties.DEFAULT_PAGE_SIZE) @ApiParam(value = "每页记录数", defaultValue = PageProperties.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return studentService.findAll(pageNum, pageSize);
    }
}
