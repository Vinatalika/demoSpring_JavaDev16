package com.example.demo.controller;

import com.example.demo.mapper.NoteMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.exception.UserNotFoundException;
import com.example.demo.service.userservice.UserService;
import com.example.demo.service.dto.NoteDto;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.exception.NoteNotFoundException;
import com.example.demo.service.noteservice.NoteService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/create")
    public String createNote(
            @RequestParam("title") @NotBlank String title,
            @RequestParam("content") @NotBlank String content,
            @RequestParam("user_id") Long userId) throws UserNotFoundException {
        NoteDto dto = new NoteDto();
        dto.setTitle(title);
        dto.setContent(content);

        UserDto userDto = userMapper.toUserDto(userService.findById(userId));
        dto.setUsername(userDto);

        noteService.add(dto);
        return "redirect:/note/list";
    }


    @GetMapping("/edit")
    public ModelAndView getNoteForEdit(@RequestParam("id") UUID id) throws NoteNotFoundException {
        ModelAndView modelAndView = new ModelAndView("editNote");
        modelAndView.addObject("note", noteMapper.toNoteResponse(noteService.getById(id)));
        return modelAndView;
    }


    @PostMapping("/update")
    public String updateNote(
            @RequestParam("id") UUID id,
            @RequestParam("title") @NotBlank String title,
            @RequestParam("content") @NotBlank String content,
            @RequestParam("user_id") Long user_id) throws NoteNotFoundException, UserNotFoundException {
        NoteDto dto = new NoteDto();
        dto.setId(id);
        dto.setTitle(title);
        dto.setContent(content);

        UserDto userDto = userMapper.toUserDto(userService.findById(user_id));
        dto.setUsername(userDto);

        noteService.update(dto);
        return "redirect:/note/list";
    }

    @GetMapping("/list")
    public ModelAndView noteList() {
        ModelAndView modelAndView = new ModelAndView("noteList");
        modelAndView.addObject("notes", noteMapper.toNoteResponses(noteService.listAll()));
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteNoteById(@RequestParam("id") UUID id) throws NoteNotFoundException {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
}
