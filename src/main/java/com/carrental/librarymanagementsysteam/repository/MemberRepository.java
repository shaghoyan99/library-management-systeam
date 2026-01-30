package com.carrental.librarymanagementsysteam.repository;

import com.carrental.librarymanagementsysteam.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
