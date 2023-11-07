package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.hibernate.jdbc.Expectation;
import org.hibernate.jdbc.Expectations;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.Skill;

import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    public HomeController() {
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	    model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }


    @PostMapping("add")
    public String processAddJobForm(
             @ModelAttribute @Valid Job newJob,
             Errors errors,
             Model model,
             @RequestParam int employerId,
             @RequestParam List<Integer> skills) {
        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        }

        Employer selectedEmployer = employerRepository.findById(employerId).orElse(new Employer());

        if (selectedEmployer != null) {
            newJob.setEmployer(selectedEmployer);
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);
            jobRepository.save(newJob);
            return "redirect:/";
    } else {
        // handles employer not found
        model.addAttribute("title", "Add Job");
        model.addAttribute("error", "Selected employer not found");
        return "add";
    }
}

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
            Job job = jobRepository.findById(jobId).orElse(new Job());
            if (job != null) {
                model.addAttribute("job", job);
                return "view";
            } else {
                model.addAttribute("title", "Job not found");
                return "error";
            }
    }
}
