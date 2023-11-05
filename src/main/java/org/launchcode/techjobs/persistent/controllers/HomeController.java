package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
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

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	    model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }


    @PostMapping("add")
    public String processAddJobForm
            (@ModelAttribute @Valid Job newJob,
             Errors errors,
             Model model,
             @RequestParam int employerId,
             @RequestParam List<Integer> skills) {
        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        }

//retrieve the employer object using employerId
        Employer selectedEmployer = employerRepository.findById(employerId).orElse(null);

        if (selectedEmployer != null) {
//set selected employer
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setEmployer(selectedEmployer);
            newJob.setSkills(skillObjs);
//save to job repository
        jobRepository.save(newJob);
        return "redirect:";
    } else {
        // handles employer not found
        model.addAttribute("title", "Add Job");
        model.addAttribute("error", "Selected employer not found");
        return "add";
    }
}

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
