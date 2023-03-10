package com.example.demo.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
