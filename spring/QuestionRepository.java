package file.thousandaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import file.thousandaire.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
