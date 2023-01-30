package ch.bbw.grademanager;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    //function to add value to array
    public static Float[] addX(int n, Float arr[], Float x)
    {
        int i;

        // create a new array of size n+1
        Float newarr[] = new Float[n + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
        }
    ViewModel viewModel = new ViewModel();

    @GetMapping("/")
    public String getGradeForm(){
        return "redirect:/GradeFormSite";
    }

    @GetMapping("GradeFormSite")
    public String getGradeForm(Model model){
        model.addAttribute("GradeOF", this.viewModel);
        return "GradeForm";
    }

    @PostMapping(value = "/GradeFormSite", params = {"button=setNewSubjectGrade"})
    public String postGradeFormsetNewSubjectGrade(ViewModel viewModelTemp, Model model) throws IOException {
        this.viewModel.setNewSubjectGrade(viewModelTemp.getNewGradeSubject(),viewModelTemp.getNewGrade());
        model.addAttribute("GradeOF", this.viewModel);
        //modifing json
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Subject> subjects = objectMapper.readValue(
                this.viewModel.getFile(),
                new TypeReference<List<Subject>>() {}
        );
        for(Subject subject : subjects){
            System.out.println(subject.getName() + "->" + this.viewModel.getNewGradeSubject());
            if (subject.getName().equals(this.viewModel.getNewGradeSubject())) {
                //adding value
                subject.setGrades(addX(subject.getGrades().length,subject.getGrades(),this.viewModel.getNewGrade()));
                objectMapper.writeValue(this.viewModel.getFile(), subjects);
            }else {}
        }
        System.out.println("postmapping setName");
        System.out.println("Subject and name in Temp: " + viewModelTemp.getNewGradeSubject() + viewModelTemp.getNewGrade());
        return "GradeForm";
    }

    @PostMapping(value = "/GradeFormSite", params = {"button=setChangeSubjectIndexGrade"})
    public String postGradeFormsetChangeSubjectIndexGrade(ViewModel viewModelTemp, Model model) throws IOException {
        this.viewModel.setChangeSubjectIndexGrade(viewModelTemp.getChangeGradeSubject(),viewModelTemp.getChangeGradeIndex(),viewModelTemp.getChangeGrade());
        model.addAttribute("GradeOF", this.viewModel);
        //modifing json
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Subject> subjects = objectMapper.readValue(
                this.viewModel.getFile(),
                new TypeReference<List<Subject>>() {}
        );
        for(Subject subject : subjects){
            if (subject.getName().equals(this.viewModel.getChangeGradeSubject())) {
                //changing value
                subject.getGrades()[this.viewModel.getChangeGradeIndex()] = this.viewModel.getChangeGrade();
                objectMapper.writeValue(this.viewModel.getFile(), subjects);
            }else {}
        }
        return "GradeForm";
    }


    @PostMapping(value = "/GradeFormSite", params = {"button=setNewSubjectName"})
    public String postGradeFormsetNewSubjectName(ViewModel viewModelTemp, Model model) throws IOException {
        this.viewModel.setNewSubjectName(viewModelTemp.getNewSubjectName());
        //making new object
        Subject newSubject = new Subject();
        newSubject.setName(this.viewModel.getNewSubjectName());
        newSubject.setGrades(new Float[]{});

        //writing to file
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Subject> subjects = objectMapper.readValue(
                this.viewModel.getFile(),
                new TypeReference<List<Subject>>() {}
        );
        subjects.add(newSubject);
        objectMapper.writeValue(this.viewModel.getFile(), subjects);
        model.addAttribute("GradeOF", this.viewModel);
        System.out.println("New subject Temp: " + viewModelTemp.getNewSubjectName());
        System.out.println("New subject Wievmodel: " + this.viewModel.getNewSubjectName());
        return "GradeForm";
    }
    @PostMapping(value = "/GradeFormSite", params = {"button=setFile"})
    public String postGradeFormsetFile(ViewModel viewModelTemp, Model model) throws IOException {
        this.viewModel.setFile(viewModelTemp.getFile());
        model.addAttribute("GradeOF", this.viewModel);
        //reading in grades
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Subject> subjects = objectMapper.readValue(
                this.viewModel.getFile(),
                new TypeReference<List<Subject>>() {}
        );
        this.viewModel.setSubjects(subjects);
        System.out.println(this.viewModel.getSubjectInfo());
        System.out.println("File path: " + viewModel.getFile().getAbsolutePath());
        return "GradeForm";
    }
}
