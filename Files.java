import java.io.File;
import java.util.ArrayList;

public class Files {
    private File[] drives;
    private ArrayList<String> rootDir;
    private ArrayList<String>[] subDir;
    private int numOfRootDir;

    public Files(){
        drives = File.listRoots();
        rootDir = new ArrayList<>();
        setRootDir(rootDir, drives);
        subDir = new ArrayList[rootDir.size()];
        fillSubDir(subDir, rootDir);
        numOfRootDir = rootDir.size();

    }

    private void setRootDir(ArrayList<String> list, File[] files){
        for (File drives : files){
            list.add(drives.getAbsolutePath());
        }
    }

    private void fillSubDir(ArrayList<String>[] targetSubDir, ArrayList<String> sourceRootDir){
        for (int i = 0; i < sourceRootDir.size(); ++i){
            targetSubDir[i] = new ArrayList<>();
            setSubDir(targetSubDir[i], sourceRootDir.get(i));
        }

    }

    public void setSubDir(ArrayList<String> subDirectory, String path){
        File buff = new File(path);
        if (buff.isDirectory()){
            try {
                for (File item : buff.listFiles()) {
                    if (item.isDirectory() && !item.isHidden()) {
                        subDirectory.add(item.getAbsolutePath());
                    }
                }
            } catch (NullPointerException e){

            }
        }
    }

    public int getNumOfRootDir(){
        return numOfRootDir;
    }

    public String getRootDir(int index){
        return rootDir.get(index);
    }

    public int getNumOfSubDir(int indexOfSubDir){
        return subDir[indexOfSubDir].size();
    }

    public String getSubDir(int indexOfRoot, int indexOfSubDir){
        return subDir[indexOfRoot].get(indexOfSubDir);
    }
}
