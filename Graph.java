import java.util.List;

public interface Graph<v> {
    public boolean add(v vertex);

    public boolean connect(v from, v to);

    public boolean disconnect(v from, v to);

    public boolean contains(v vertex);

    public List<Comercio> getInwardEdges(v to);

    public List<Comercio> getOutwardEdges(v from);

    public List<Comercio> getVerticesConnectedTo(v vertex);

    public List<Comercio> getAllVertices();

    public boolean remove(v vertex);

    public int size();
}