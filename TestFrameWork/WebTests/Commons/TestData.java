package TestFrameWork.WebTests.Commons;

import java.util.Arrays;
import java.util.List;

public class TestData {

    public static class WhoWeServeFeature{
        public static String[] list = {"Students", "Instructors", "Book Authors", "Professionals", "Researchers", "Institutions",
                "Librarians", "Corporations", "Societies", "Journal Editors", "Government"};
        public static List<String> groupsOnPage = Arrays.asList(list);
        public static int groupsNumber = 11;
    }

    public static class EducationFeature{
         public static String[] expectedEducationOptions = new String[]{
                 "Information & Library Science", "Education & Public Policy", "K-12 General",
                "Higher Education General", "Vocational Technology",
                "Conflict Resolution & Mediation (School settings)", "Curriculum Tools- General",
                "Special Educational Needs", "Theory of Education", "Education Special Topics",
                "Educational Research & Statistics", "Literacy & Reading", "Classroom Management"};
    }
}
