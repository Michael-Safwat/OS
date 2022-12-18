package project;
import static project.ReadersWritersProblem.readCount;
import static project.ReadersWritersProblem.readLock;
import static project.ReadersWritersProblem.writeLock;
import static project.ReadersWritersProblem.file;
import static project.ReadersWritersProblem.path;
import java.io.*;
class W implements Runnable { // Writing Process

    @Override
    public void run() {
        try {
            writeLock.acquire(); //wait(rw_mutex)
            
            System.out.println("Thread "+Thread.currentThread().getName() + " is writing");
            if(!file.exists()){
                
                try {
                    file.createNewFile();
                } catch(IOException e){
                    return;
                }
            }
            
            try {                
                try (FileOutputStream fout =
                        new FileOutputStream(path, true)) {
                    fout.write("sonhdhsh\r\n".getBytes());
                }
            }catch (IOException e){
                 return;
            }
                
            System.out.println("Thread "+Thread.currentThread().getName() + " has finished writing");

            writeLock.release();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
