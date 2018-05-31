package file.thousandaire.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import file.thousandaire.domain.DifficultyLevel;
import file.thousandaire.domain.Topic;
import file.thousandaire.repository.DifficultyLevelRepository;
import file.thousandaire.repository.TopicRepository;

@Controller
public class LevelController {

    @Autowired
    private HttpSession session;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private DifficultyLevelRepository difficultyLevelRepository;
     @PostConstruct
    public void init() {
        difficultyLevelRepository.save(new DifficultyLevel(12345678910L));
    }

    @RequestMapping("/topics/{topicId}/levels/{levelId}")
    public String getTopicLevel(Model model, @PathVariable Long topicId, @PathVariable Long levelId) {
        if (!topicId.equals(session.getAttribute("topic"))) {
            return "redirect:/topics/" + session.getAttribute("topic");
        }

        if (!levelId.equals(session.getAttribute("level"))) {
            return "redirect:/topics/" + topicId + "/levels/" + session.getAttribute("level");
        }

        Topic topic = topicRepository.findOne(topicId);

        model.addAttribute("topic", topic);

        List<DifficultyLevel> difficultyLevels = difficultyLevelRepository.findByTopic(topic);
        model.addAttribute("levels", difficultyLevels);

        Long max = difficultyLevels.stream().map(d -> d.getLevel()).max(Long::compareTo).get();
        if (levelId > max) {
            
            return "redirect:/finish";
        }

        return "levels";
    }

}
