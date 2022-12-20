package com.saidinit.random.wisebear.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Match {
    private Player p1;
    private Player p2;
    private int table;
}
