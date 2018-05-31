package file.thousandaire.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class DifficultyLevel extends AbstractPersistable<Long>  implements Comparable<DifficultyLevel>{

    private Long level;
    private Long reward;
    public DifficultyLevel() {
    }
    public DifficultyLevel(Long level) {
        this.level = level;
    }

    @ManyToOne
    private Topic topic;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
       @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.level);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DifficultyLevel other = (DifficultyLevel) obj;
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(DifficultyLevel o) {
        return this.level.compareTo(o.level);
    }

}
