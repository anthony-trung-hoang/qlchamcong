package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.Timekeeper;

import java.util.List;

public interface ITimekeeperRepository {
    List<Timekeeper> getAllTimekeeper();
    Timekeeper getTimekeeperByCode(String timeKeeperCode);
}
