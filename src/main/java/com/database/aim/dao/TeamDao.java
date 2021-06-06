package com.database.aim.dao;

import com.database.aim.pojo.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDao extends JpaRepository<Team, Integer> {
    Team findTeamById(int Id);
}
