package cn.edu.jlu.po;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TurnOverBody {
    private Integer shopId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
