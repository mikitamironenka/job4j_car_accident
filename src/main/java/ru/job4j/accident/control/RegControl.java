package ru.job4j.accident.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AccidentService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final AccidentService accidentService;

    public RegControl(PasswordEncoder encoder, AccidentService accidentService) {
        this.encoder = encoder;
        this.accidentService = accidentService;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(accidentService.findByAuthority("ROLE_USER"));
        accidentService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute Accident accident) {
        return "reg";
    }
}
