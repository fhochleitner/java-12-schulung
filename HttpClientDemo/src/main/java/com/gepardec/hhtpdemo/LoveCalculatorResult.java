package com.gepardec.hhtpdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoveCalculatorResult {

    private String fname;
    private String sname;
    private String result;
    private int percentage;

}
