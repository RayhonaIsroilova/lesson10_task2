package uz.pdp.lesson10_task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson10_task2.entity.Faculty;
import uz.pdp.lesson10_task2.entity.Group;
import uz.pdp.lesson10_task2.payload.GroupDto;
import uz.pdp.lesson10_task2.repository.FacultyRepository;
import uz.pdp.lesson10_task2.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    //VAZIRLIK UCHUN
    //READ
    @GetMapping
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }


    //UNIVERSITET MAS'UL XODIMI UCHUN
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId) {
        List<Group> allByFaculty_universityId = groupRepository.findAllByFaculty_UniversityId(universityId);
        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);
        return allByFaculty_universityId;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {

        Group group = new Group();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()) {
            return "Such faculty not found";
        }
        group.setFaculty(optionalFaculty.get());

        groupRepository.save(group);
        return "Group added";
    }

    @PutMapping("/{id}")
    public String editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            Group group = byId.get();
            group.setName(groupDto.getName());
            Optional<Faculty> byId1 = facultyRepository.findById(groupDto.getFacultyId());
            if (!byId1.isPresent()) {
                return "Faculty not found";
            }
            group.setFaculty(byId1.get());
            groupRepository.save(group);
            return "Group edited";

        }
        return "Group not found";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Integer id) {
        try {
            groupRepository.deleteById(id);
            return "Group deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }

}
