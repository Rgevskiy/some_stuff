import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Rgevskiy on 10.07.2017.
 */
public class FMGUI extends JFrame {
    private JTree dirs;
    private String ROOT;
    private Files system;

    public FMGUI(){
        super("FileManager by VRF2017");
        ROOT = "Устройство";
        system = new Files();
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel();
        TreeModel model = createTree();
        dirs = new JTree(model);
        dirs.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        dirs.setSize(200,400);
        content.add(new JScrollPane(dirs));
        getContentPane().add(content, BorderLayout.WEST);

        this.setVisible(true);
    }

    private TreeModel createTree(){
        // Создаем корень дерева системы
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(ROOT);
        // Добавляем список доступных дисков
        for (int i = 0; i < system.getNumOfRootDir(); ++i){
            root.add(new DefaultMutableTreeNode(system.getRootDir(i)));
        }
        // Добавляем первый уровень каталогов на диске
        for (int i = 0; i < system.getNumOfRootDir(); ++i){
            for (int j = 0; j < system.getNumOfSubDir(i); ++j){
                DefaultMutableTreeNode tempNode = (DefaultMutableTreeNode) root.getChildAt(i);
                tempNode.add(new DefaultMutableTreeNode(system.getSubDir(i, j)));
            }
        }

        return new DefaultTreeModel(root);
    }
}
