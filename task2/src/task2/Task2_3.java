package task2;

public class Task2_3
{
    private static class Point
    {
        public double x;
        public double y;
        Point(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
        public double getDistance(Point p)
        {
            return Math.sqrt(Math.pow((x - p.x), 2) + Math.pow((y - p.y), 2));
        }
    }
    private static class Triangle
    {
        Triangle(Point p1, Point p2, Point p3)
        {
            p1_ = p1;
            p2_ = p2;
            p3_ = p3;
        }
        public double getArea()
        {
            return 0.5 * Math.abs((p2_.x - p1_.x)*(p3_.y - p1_.y)-(p3_.x- p1_.x)*(p2_.y- p1_.y));
        }
        public double getPerimeter()
        {
            return p1_.getDistance(p3_) + p3_.getDistance(p2_) + p2_.getDistance(p1_);
        }
        public Point getPointOfIntersectionOfMedians()
        {
            return new Point((p1_.x + p2_.x + p3_.x)/3, (p1_.y + p2_.y + p3_.y)/3);
        }

        private Point p1_;
        private Point p2_;
        private Point p3_;
    }
    public static void main(String[] args)
    {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(5, 8);
        Point p3 = new Point(-1, -5);
        Triangle triangle = new Triangle(p1, p2, p3);
        System.out.println("Area: " + triangle.getArea());
        System.out.println("Perimeter: " + triangle.getPerimeter());
        System.out.println("Point of intersection of medians: " + triangle.getPointOfIntersectionOfMedians().x + " " + triangle.getPointOfIntersectionOfMedians().y);
    }
}
