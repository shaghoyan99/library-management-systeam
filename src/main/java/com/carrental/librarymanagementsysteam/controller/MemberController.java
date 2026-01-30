package com.carrental.librarymanagementsysteam.controller;

import com.carrental.librarymanagementsysteam.model.Book;
import com.carrental.librarymanagementsysteam.model.Member;
import com.carrental.librarymanagementsysteam.repository.BookRepository;
import com.carrental.librarymanagementsysteam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "member";
    }

    @GetMapping("/members/details")
    public String getMemberDetails(@RequestParam("id") int id, Model model) {

        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            model.addAttribute("member", member);
            model.addAttribute("borrowedBooks", member.getBorrowedBooks());
            return "member-details";
        }

        return "redirect:/members";
    }

    @PostMapping("/members/borrow")
    public String borrowBook(@RequestParam("memberId") int memberId, @RequestParam("bookId") int bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();
        member.getBorrowedBooks().add(book);
        memberRepository.save(member);
        return "redirect:/members";
    }

    @GetMapping("/members/add")
    public String addMember() {
        return "add-member";
    }

    @PostMapping("/members/add")
    public String addMemberPost(@ModelAttribute Member member) {
        member.setRegistrationDate(LocalDateTime.now());
        memberRepository.save(member);
        return "redirect:/members";
    }

    @GetMapping("/members/delete")
    public String deleteMember(@RequestParam("id") int id) {
        memberRepository.deleteById(id);
        return "redirect:/members";
    }

    @GetMapping("/members/details/delete")
    public String deleteBorrowedBook(@RequestParam("memberId") int memberId, @RequestParam("bookId") int bookId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            List<Book> borrowedBooks = member.getBorrowedBooks();
            borrowedBooks.removeIf(book -> book.getId() == bookId);
            member.setBorrowedBooks(borrowedBooks);
            memberRepository.save(member);
        }
        return "redirect:/members/details?id=" + memberId;
    }
}
