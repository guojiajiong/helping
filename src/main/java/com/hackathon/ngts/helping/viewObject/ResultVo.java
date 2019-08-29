package com.hackathon.ngts.helping.viewObject;

import lombok.Data;

import java.util.List;

/**
 * @author guojiajiong
 * @date 2019-08-29 21:49
 */
@Data
public class ResultVo<T> {

    private boolean error;

    private String errorMessage;

    private T object;

}
