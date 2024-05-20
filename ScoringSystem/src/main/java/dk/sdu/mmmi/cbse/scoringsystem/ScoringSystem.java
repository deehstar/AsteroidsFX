package dk.sdu.mmmi.cbse.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/scoringsystem")
public class ScoringSystem {
  private Long score = 0L;

  public static void main(String[] args) {
    SpringApplication.run(ScoringSystem.class, args);
  }

  @GetMapping("/score")
  public Long getScore() {
    return score;
  }

  @PutMapping("/score/update/{value}")
  public Long updateScore(@PathVariable(value = "value") Long value) {
    score += value;
    return score;
  }
}
