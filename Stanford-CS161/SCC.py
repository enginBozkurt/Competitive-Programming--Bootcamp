# Implementation of directed graphs#

class CS161Vertex:
    def __init__(self, v):
        self.inNeighbors = []
        self.outNeighbors = []
        self.value = v
        # useful for DFS/BFS
        self.inTime = None
        self.outTime = None
        self.status = "unvisited"
        
    def hasOutNeighbor(self,v):
        if v in self.outNeighbors:
            return True
        return False
        
    def hasInNeighbor(self,v):
        if v in self.inNeighbors:
            return True
        return False
    
    def hasNeighbor(self,v):
        if v in self.inNeighbors or v in self.outNeighbors:
            return True
        return False
    
    def getOutNeighbors(self):
        return self.outNeighbors
    
    def getInNeighbors(self):
        return self.inNeighbors
        
    def addOutNeighbor(self,v):
        self.outNeighbors.append(v)
    
    def addInNeighbor(self,v):
        self.inNeighbors.append(v)
    
    def __str__(self):
        return str(self.value) 
        
# This is a directed graph class for use in CS161.
# It can also be used as an undirected graph by adding edges in both directions.
class CS161Graph:
    def __init__(self):
        self.vertices = []

    def addVertex(self,n):
        self.vertices.append(n)
        
    # add a directed edge from CS161Node u to CS161Node v
    def addDiEdge(self,u,v):
        u.addOutNeighbor(v)
        v.addInNeighbor(u)
    
    # add edges in both directions between u and v
    def addBiEdge(self,u,v):
        self.addDiEdge(u,v)
        self.addDiEdge(v,u)
        
    # reverse the edge between u and v.  Multiple edges are not supported.
    def reverseEdge(self,u,v):
        if u.hasOutNeighbor(v) and v.hasInNeighbor(u):
            if v.hasOutNeighbor(u) and u.hasInNeighbor(v): 
                return
            self.addDiEdge(v,u)
            u.outNeighbors.remove(v)
            v.inNeighbors.remove(u)

    # get a list of all the directed edges
    # directed edges are a list of two vertices
    def getDirEdges(self):
        ret = []
        for v in self.vertices:
            ret += [ [v, u] for u in v.outNeighbors ]
        return ret
    
    def __str__(self):
        ret = "CS161Graph with:\n"
        ret += "\t Vertices:\n\t"
        for v in self.vertices:
            ret += str(v) + ","
        ret += "\n"
        ret += "\t Edges:\n\t"
        for a,b in self.getDirEdges():
            ret += "(" + str(a) + "," + str(b) + ") "
        ret += "\n"
        return ret
    

stanford = CS161Vertex("Stanford")
wiki = CS161Vertex("Wikipedia")
nytimes = CS161Vertex("NYTimes")
cal = CS161Vertex("Berkeley")
puppies = CS161Vertex("Puppies")
google = CS161Vertex("Google")

G = CS161Graph()

V = [ stanford, wiki, nytimes, cal, puppies, google ]

for v in V:
    G.addVertex(v)

E = [ (stanford, wiki), (stanford, puppies), (wiki, stanford), (wiki, nytimes), (nytimes, stanford), (cal, stanford), (cal, puppies), (wiki,puppies), (nytimes, puppies), (puppies, google), (google, puppies) ]

for x, y in E:
    G.addDiEdge(x,y)
    
print(G)

# Now let's implement our SCC algorithm.

def DFS_helper(w, currentTime, ordering, verbose):
    if verbose:
        print("Time", currentTime, ":\t entering", w)
    
    w.inTime = currentTime
    currentTime += 1
    
    w.status = "inprogress"
    
    for v in w.getOutNeighbors():
        if v.status == "unvisited":
            currentTime = DFS_helper(v, currentTime, ordering, verbose)
            currentTime += 1
        
    w.outTime = currentTime
    w.status = "done"
    ordering.insert(0, w)
    
    if verbose:
        print("Time", currentTime, ":\t leaving", w)
    
    return currentTime

# This is a version of DFS which outputs vertices in the order that DFS leaves them.
def SCC(G, verbose=False):
    ordering = []
    for v in G.vertices:
        v.status = "unvisited"
        v.inTime = None
        v.outTime = None
    currentTime = 0
    for w in G.vertices:
        if w.status == "unvisited":
            currentTime = DFS_helper( w, currentTime, ordering, verbose )
        currentTime += 1
    
    #now reverse all edges
    E = G.getDirEdges()
    
    for x, y in E:
        G.reverseEdge(x, y)
    
    # and do it (second DFS) again, but this tim in the order "ordering"
    SCCs = []
    
    for v in ordering:
        v.status = "unvisited"
        v.inTime = None
        v.outTime = None
    currentTime = 0
    
    for w in ordering:
        visited = []
        if w.status == "unvisited":
            currentTime = DFS_helper(w, currentTime, visited, verbose)
            SCCs.append(visited[:])    
    return SCCs

SCCs = SCC(G, False)

for X in SCCs:
    print ([str(x) for x in X])
   