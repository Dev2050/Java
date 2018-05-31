package sec.thousandaire.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sec.thousandaire.domain.DifficultyLevel;
import sec.thousandaire.domain.Topic;

public interface DifficultyLevelRepository extends JpaRepository<DifficultyLevel, Long> {
    List<DifficultyLevel> findByTopic(Topic topic);
    DifficultyLevel findByTopicAndLevel(Topic topic, Long level);
}
