package com.carrental.librarymanagementsysteam.service.imple;

import com.carrental.librarymanagementsysteam.exception.MemberNotFoundException;
import com.carrental.librarymanagementsysteam.model.Book;
import com.carrental.librarymanagementsysteam.model.Member;
import com.carrental.librarymanagementsysteam.repository.MemberRepository;
import com.carrental.librarymanagementsysteam.service.BookService;
import com.carrental.librarymanagementsysteam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BookService bookService;

    @Value("${library.management.upload.image.directory.path}")
    private String imageDirectoryPath;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Integer id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));
    }

    @Override
    public void save(Member member, MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);
            try{
                multipartFile.transferTo(file);
                member.setPictureName(fileName);
            }catch (IOException e){
                throw new RuntimeException("File not found");
            }
        }
        member.setRegistrationDate(LocalDateTime.now());
        memberRepository.save(member);
    }

    @Override
    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void deleteBorrowedBook(Integer memberId, Integer bookId) {
        memberRepository.findById(memberId).ifPresent(member -> {
            List<Book> borrowedBooks = member.getBorrowedBooks();
            borrowedBooks.removeIf(book -> book.getId() == bookId);
            member.setBorrowedBooks(borrowedBooks);
            memberRepository.save(member);
        });
    }

    @Override
    public void addBorrowedBook(Integer memberId, Integer bookId) {
        memberRepository.findById(memberId).ifPresent(member -> {
            Book book = bookService.findById(bookId);
            member.getBorrowedBooks().add(book);
            memberRepository.save(member);
        });
    }
}
