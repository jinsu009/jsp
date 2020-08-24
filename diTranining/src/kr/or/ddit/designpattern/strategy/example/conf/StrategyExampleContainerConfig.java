package kr.or.ddit.designpattern.strategy.example.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
@ComponentScan(basePackages = "kr.or.ddit.designpattern.strategy.example")
public class StrategyExampleContainerConfig {


}
