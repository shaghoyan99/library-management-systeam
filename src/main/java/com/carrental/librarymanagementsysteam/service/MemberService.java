package com.carrental.librarymanagementsysteam.service;

import com.carrental.librarymanagementsysteam.model.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface MemberService {

    List<Member> findAll();

    Member findById(Integer id);

    void save(Member member, MultipartFile multipartFile);

    void deleteById(Integer id);

    void deleteBorrowedBook(Integer memberId, Integer bookId);

    void addBorrowedBook(Integer memberId, Integer bookId);


}
