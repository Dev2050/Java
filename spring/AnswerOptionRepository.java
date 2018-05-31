package sec.thousandaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.thousandaire.domain.AnswerOption;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
}
