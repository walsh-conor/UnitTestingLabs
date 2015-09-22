package agile.labs.unit;

import java.util.List;
import java.util.Vector;

 public class Module {
       public static final int wksPerSemester = 12 ;  
       private String name ;
       private int noLectures ;  // no. lectures per week
       private int credits;
       private List<Topic> topics = new Vector<Topic>();

   public Module(String name, int noLectures, int credits) {
        this.name = name;
        this.noLectures = noLectures;
        this.credits = credits;
   }

    public boolean addTopic (Topic t) {
         boolean result = false ;
         if ( (computeTopicTotal() + t.getNoLectures()) 
                   <= this.noLectures * wksPerSemester ) {
             topics.add(t);
             result = true;
         }
         return result ;
     }

    public Topic findTopic (String name) {
         Topic result = null ;
         for (Topic t : topics) {
                if (t.getName().equalsIgnoreCase(name)) {
                    result = t ;
                    break ;
                }
         }
         return result ;
    }

    private int computeTopicTotal() {
        int result = 0 ;
        for (Topic t : topics) {
            result += t.getNoLectures();
        }
        return result;
    }
    
    public Topic changeTopic(String name, Topic changes) {
        Topic currentTopic = findTopic(name); 
        Topic newTopic = null ;
        if (changes.getNoLectures() <= 0) {
            newTopic = new Topic(currentTopic.getNoLectures(), changes.getName());
        } else {
            newTopic = new Topic(changes.getNoLectures(), changes.getName());
        }
        removeTopic(currentTopic.getName() );
        addTopic(newTopic) ;
        return newTopic;
    }
    
    public Topic removeTopic(String name) {
        Topic result = findTopic(name) ;
        if (result != null) {
            topics.remove(result) ;
        }
        return result;
    }

    public Topic mergeTopics(String name1, String name2,String nemName) {
        Topic merged = null;
        Topic topic1 = findTopic(name1) ;
        Topic topic2 = findTopic(name2) ;
        if ( topic1 != null && topic2 != null) {
            merged = new Topic( 
                    (topic1.getNoLectures() + topic2.getNoLectures()),
                    nemName);
            removeTopic(topic1.getName());
            removeTopic(topic2.getName());
            addTopic(merged);
        }
      return merged;
    }

    private Boolean checkNameClash(String name) {
        Boolean result = true ;
        for (Topic t : topics) {
            if (t.getName().equalsIgnoreCase(name)) {
                result = false ;
                break ;
            }
        }
        return result ;
     }
}