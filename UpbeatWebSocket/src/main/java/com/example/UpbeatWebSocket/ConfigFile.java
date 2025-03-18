package com.example.UpbeatWebSocket;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConfigFile {
    private int row ;
    private int col ;
    private  int init_budget ;
    private int init_center_dep ;
    private int rev_cost ;
    private int max_dep ;
    private int interest_pct ;
}
