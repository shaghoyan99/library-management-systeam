package com.carrental.librarymanagementsysteam.controller;

import com.carrental.librarymanagementsysteam.model.Member;
import com.carrental.librarymanagementsysteam.service.BookService;
import com.carrental.librarymanagementsysteam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("books", bookService.findAll());
        return "member";
    }

    @GetMapping("/members/details")
    public String getMemberDetails(@RequestParam("id") int id, Model model) {
            Member member = memberService.findById(id);
            model.addAttribute("member", member);
            model.addAttribute("borrowedBooks", member.getBorrowedBooks());
            return "member-details";
    }

    @PostMapping("/members/borrow")
    public String borrowBook(@RequestParam("memberId") int memberId, @RequestParam("bookId") int bookId) {
        memberService.addBorrowedBook(memberId, bookId);
        return "redirect:/members";
    }

    @GetMapping("/members/add")
    public String addMember() {
        return "add-member";
    }

    @PostMapping("/members/add")
    public String addMemberPost(@ModelAttribute Member member, @RequestParam("pic") MultipartFile multipartFile) {
        memberService.save(member,multipartFile);
        return "redirect:/members";
    }

    @GetMapping("/members/delete")
    public String deleteMember(@RequestParam("id") int id) {
        memberService.deleteById(id);
        return "redirect:/members";
    }

    @GetMapping("/members/details/delete")
    public String deleteBorrowedBook(@RequestParam("memberId") int memberId, @RequestParam("bookId") int bookId) {
        memberService.deleteBorrowedBook(memberId, bookId);
        return "redirect:/members/details?id=" + memberId;
    }
}
