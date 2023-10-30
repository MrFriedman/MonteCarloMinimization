package MonteCarloMini;

import java.util.concurrent.RecursiveTask;

import MonteCarloMini.Parallel.Return;

public class Parallel extends RecursiveTask<Return> { // 
    public int hi;
    public int lo;
    public Search[] searches;

    public Parallel(Search[] _searches, int _lo, int _hi)
    {
        this.searches = _searches;
        this.lo = _lo;
        this.hi = _hi;
    }

    protected Return compute(){
        if ((hi-lo) <= (searches.length / 8))
        {
            int min=Integer.MAX_VALUE;
    	    int local_min=Integer.MAX_VALUE;
    	    int finder =-1;
            for (int i = lo; i<hi; i++)
            {
                local_min=searches[i].find_valleys();
    		    if((!searches[i].isStopped())&&(local_min<min)) { //don't look at  those who stopped because hit exisiting path
    			min=local_min;
    			finder=i; //keep track of who found it
    		    }
            }
            return new Return(min, finder);
            // Store searches[finder] somewhere as the answer
        }
        else
        {
            Parallel left = new Parallel(searches, lo, (hi+lo)/2);
            Parallel right = new Parallel(searches, (hi+lo)/2, hi);
            left.fork();
            Return rightAns = right.compute();
            Return leftAns = left.join();
            if (rightAns.getMin() <= leftAns.getMin())
            {return rightAns;}
            else
            {return leftAns;}
            
        }

    }
    public class Return {
        public int min;
        public int finder;
            public Return(int _min, int _finder)
            {
                this.min = _min;
                this.finder = _finder;
            }

            public int getMin(){
                return this.min;
            }
            public int getFinder(){
                return this.finder;
            }
        }
}
