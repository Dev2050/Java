package file.thousandaire.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import file.thousandaire.domain.Topic;
import file.thousandaire.repository.TopicRepository;

@Controller
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @PostConstruct
    public void init() {
        topicRepository.save(new Topic("House"));
    }
    @Autowired
    private HttpSession session;

    @RequestMapping("/topics")
    public String listTopics(Model model) {
        model.addAttribute("topics", topicRepository.findAll());
        return "topics";
    }

    @RequestMapping("/topics/{topicId}")
    public String listTopics(Model model, @PathVariable Long topicId) {
        session.setAttribute("topic", topicId);
        session.setAttribute("level", new Long(1));
        model.addAttribute("topic", topicRepository.findOne(topicId));
        return "topic";
    }
}
