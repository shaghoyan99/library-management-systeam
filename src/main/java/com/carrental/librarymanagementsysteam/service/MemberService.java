package com.carrental.librarymanagementsysteam.service;

import com.carrental.librarymanagementsysteam.model.Member;

import java.util.List;
public interface MemberService {

    List<Member> findAll();

    Member findById(Integer id);

    void save(Member member);

    void deleteById(Integer id);

    void deleteBorrowedBook(Integer memberId, Integer bookId);

    void addBorrowedBook(Integer memberId, Integer bookId);


}
