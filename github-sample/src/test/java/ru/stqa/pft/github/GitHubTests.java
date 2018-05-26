package ru.stqa.pft.github;

import com.jcabi.github.*;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    // прочитаем и ивыведем на консоль историю изменений какого-нибудь  репозитория
    //  соединение с GitHub через удаленный интерфейс
    Github github=new RtGithub("37bf107bfa0e87d31dbbb1a896079ce94071bd9a"); //сгенерированный на GitHub token
    RepoCommits commits=github.repos().get(new Coordinates.Simple("avryzhikov","java_pft")).commits();
    for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String,String>().build())) {

      System.out.println(commit);
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
