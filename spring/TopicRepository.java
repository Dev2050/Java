package file.thousandaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import file.thousandaire.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
