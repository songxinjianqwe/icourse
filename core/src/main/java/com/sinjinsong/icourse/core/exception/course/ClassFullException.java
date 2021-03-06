package com.sinjinsong.icourse.core.exception.course;

import com.sinjinsong.icourse.common.exception.BaseRestException;
import com.sinjinsong.icourse.common.exception.annotation.RestField;
import com.sinjinsong.icourse.common.exception.annotation.RestResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author sinjinsong
 * @date 2018/4/6
 */
@RestField("classId")
@RestResponseStatus(value=HttpStatus.BAD_REQUEST,code=40013)
public class ClassFullException extends BaseRestException  {
    public ClassFullException(Long classId) {
        super(classId);
    }
}
