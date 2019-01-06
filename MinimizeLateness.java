package fendji;

import java.util.Arrays;

class Job implements Comparable<Job>{
	public String name;
	public int processingTime;
	public int dueDate;
	public int start;
	public int finish;
        public int release;


	public Job(String name, int processingTime, int dueDate, int release){
		this.name=name;
		this.processingTime=processingTime;
		this.dueDate=dueDate;
                this.release = release;
	}

	public int compareTo(Job job) {
          
            if (this.release < job.release){ 
                if (this.dueDate < job.dueDate) {
                     return this.dueDate;
                }
                return job.dueDate;
            }
            return this.dueDate - job.dueDate;
               
	}
        
        
	@Override
	public String toString(){
		return "[Job " + name + ":  Processing Time="+processingTime + ", due="+dueDate + ", release="+release + ",   (start="+start + ", finish="+finish + ")]";
	}
}

public class MinimizeLateness {

	public static void  findMinLateness(Job[] jobs){
		Arrays.sort(jobs);		//Sort by earliest deadline

		int currentIntervalEndTime = 0;
		for(Job job : jobs){
			job.start = currentIntervalEndTime;
			job.finish = currentIntervalEndTime + job.processingTime;
			currentIntervalEndTime += job.processingTime;
		}
                

		//Loop over jobs to find any that are late and find the maximum lateness value
		int maxLateness=0;
		String maxLateJob = "";		//Initialize to empty string in case no job is late
		for(Job job : jobs){
			int lateness = job.finish - job.dueDate;	//Calculate lateness. Negative means it's NOT late
			if(lateness > maxLateness){
				maxLateness = lateness;
				maxLateJob = job.name;
			}
		}

		System.out.print("Max Lateness = " + maxLateness);
		if(!maxLateJob.equals("")){				//Only printed if a job was found. Skipped if no jobs are late
			System.out.print("  From Job " + maxLateJob);
		}
		System.out.println("\nJob Assignment Order");
		for(Job job : jobs){
			System.out.println(job);
		}
	}

        
	public static void main(String[] args) {
		Job[] jobs = {
				/*new Job("1", 4, 8, 0),
				new Job("2", 2, 12, 1),
				new Job("3", 6, 11, 2),
				new Job("4", 5, 10, 3), */
                                new Job("1", 4, 8, 0),
				new Job("2", 5, 10, 3),
				new Job("3", 6, 11, 2),
				new Job("4", 2, 12, 1), 
				
		};
		MinimizeLateness.findMinLateness(jobs);
	}



}
