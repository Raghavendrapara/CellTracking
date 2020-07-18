package imag;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class DendrogramPaintTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DendrogramPaintPanel panel = new DendrogramPaintPanel();
        f.getContentPane().add(panel);

        f.setSize(1000,800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class Node<T> 
{
    private final T contents;
    private final List<Node<T>> children;

    Node(T contents)
    {
        this.contents = contents;
        this.children = Collections.emptyList();
    }

    Node(Node<T> child0, Node<T> child1)
    {
        this.contents = null;

        List<Node<T>> list = new ArrayList<Node<T>>();
        list.add(child0);
        list.add(child1);
        this.children = Collections.unmodifiableList(list);
    }

    public T getContents()
    {
        return contents;
    }

    public List<Node<T>> getChildren()
    {
        return Collections.unmodifiableList(children);
    }
}


class DendrogramPaintPanel extends JPanel
{
    private static <T> Node<T> create(T contents)
    {
        return new Node<T>(contents);
    }
    private static <T> Node<T> create(Node<T> child0, Node<T> child1)
    {
        return new Node<T>(child0, child1);
    }


    private Node<String> root;
    private int leaves;
    private int levels;
    private int heightPerLeaf;
    private int widthPerLevel;
    private int currentY;
    private final int margin = 25;

    DendrogramPaintPanel()
    {
        root =
            create(
                create(
                    create("10"),
                    create(
                        create("9"),
                        create(
                            create("8"), 
                            create("7")
                        )
                    )
                ),
                create(
                    create(
                        create("6"),
                        create("5")
                    ),
                    create(
                        create("4"),
                        create(
                            create("3"),
                            create(
                                create("2"),
                                create("1")
                            )
                        )
                    )
                )
            );
    }

    private static <T> int countLeaves(Node<T> node)
    {
        List<Node<T>> children = node.getChildren();
        if (children.size() == 0)
        {
            return 1;
        }
        Node<T> child0 = children.get(0);
        Node<T> child1 = children.get(1);
        return countLeaves(child0) + countLeaves(child1);
    }

    private static <T> int countLevels(Node<T> node)
    {
        List<Node<T>> children = node.getChildren();
        if (children.size() == 0)
        {
            return 1;
        }
        Node<T> child0 = children.get(0);
        Node<T> child1 = children.get(1);
        return 1+Math.max(countLevels(child0), countLevels(child1));
    }


    @Override
    protected void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;

        leaves = countLeaves(root);
        levels = countLevels(root);
        heightPerLeaf = (getHeight() - margin - margin) / leaves;
        widthPerLevel = (getWidth() - margin - margin)/ levels;
        currentY = 0;

        g.translate(margin, margin);
        draw(g, root, 0);
    }


    private <T> Point draw(Graphics g, Node<T> node, int y)
    {
        List<Node<T>> children = node.getChildren();
        if (children.size() == 0)
        {
            int x = getWidth() - widthPerLevel - 2 * margin;
            g.drawString(String.valueOf(node.getContents()), x+8, currentY+8);
            int resultX = x;
            int resultY = currentY;
            currentY += heightPerLeaf;
            return new Point(resultX, resultY);
        }
        if (children.size() >= 2)
        {
            Node<T> child0 = children.get(0);
            Node<T> child1 = children.get(1);
            Point p0 = draw(g, child0, y);
            Point p1 = draw(g, child1, y+heightPerLeaf);

            g.fillRect(p0.x-2, p0.y-2, 4, 4);
            g.fillRect(p1.x-2, p1.y-2, 4, 4);
            int dx = widthPerLevel;
            int vx = Math.min(p0.x-dx, p1.x-dx);
            g.drawLine(vx, p0.y, p0.x, p0.y);
            g.drawLine(vx, p1.y, p1.x, p1.y);
            g.drawLine(vx, p0.y, vx, p1.y);
            Point p = new Point(vx, p0.y+(p1.y - p0.y)/2);
            return p;
        }
        // Should never happen
        return new Point();
    }
}