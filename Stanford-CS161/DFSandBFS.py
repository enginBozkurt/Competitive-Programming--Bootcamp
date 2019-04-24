# -*- coding: utf-8 -*-
"""
We'll  implement BFS and DFS, alsong with a few applications

@author: Engin
"""
class CustomVertex:
    def __init__(self,v):
        self.inNeighbors = []
        self.outNeighbors = []
        self.value = v
        # useful for DFS
        self.inTime = None
        self.outTime = None
        self.status = "unvisited"
    
    def hasOutNeighbor(self, v):
        if v in self.outNeighbors:
            return True
        return False
    
    def hasInNeighbor(self, v):
        if v in self.inNeighbors:
            return True
        return False
    
    def hasNeighbor(self, v):
        if v in self.inNeighbors or self.outNeighbors:
            return True
        return False

    def getOutNeighbors(self):
        return self.outNeighbors
    
    def getInNeighbors(self):
        return self.inNeighbors
    
    def addOutNeighbor(self,v):
        self.outNeighbors.append(v)
    
    def addInNeighbor(self, v):
        self.inNeighbors.append(v)
    
    def __str__(self):
        return str(self.value) 
    
    # This is a directed graph class 
    # It can also be used as an undirected graph by adding edges in both directions.
    
class CustomGraph:
    def __init__(self):
        self.vertices = []
    
    def addVertex(self, n):
        self.vertices.append(n)
    
    #add directed edge from u to v
    def addDiEdge(self, u, v):
        u.addOutNeighbor(v)
        v.addInNeighbor(u)
    
    # add edges in both directions between u and v
    def addBiEdge(self, u, v):
        self.addDiEdge(u, v)
        self.addDiEdge(v,u)
    
    # get a list of all the directed edges
    # directed edges are a list of two vertices
    def getDirEdges(self):
        ret = []
        for v in self.vertices:
            ret += [ [v, u] for u in v.outNeighbors ]
        return ret
    
    def __str__(self):
        ret = "Graph with:\n"
        ret += "\t Vertices:\n\t"
        for v in self.vertices:
            ret += str(v) + ","
        ret += "\n"
        ret += "\t Edges:\n\t"
        for a,b in self.getDirEdges():
            ret += "(" + str(a) + "," + str(b) + ") "
        ret += "\n"
        return ret
    
# Quick sanity-check
G = CustomGraph()
for i in range(10):
    G.addVertex( CustomVertex(i) )

V = G.vertices

for i in range(9):
    G.addDiEdge( V[i], V[i+1] )

print(G)


#BFS

def BFS(w, G):
    for v in G.vertices:
        v.status = "unvisited"
    n = len(G.vertices)
    Ls = [ [] for i in range(n) ]
    Ls[0] = [w]
    w.status = "visited"
    for i in range(n):
        for u in Ls[i]:
            for v in u.getOutNeighbors():
                if v.status == "unvisited":
                    v.status = "visited"
                    Ls[i+1].append(v)
    return Ls


#Application: distance
newGraph = CustomGraph()

for i in range(6):
    newGraph.addVertex( CustomVertex( i ) ) 

    
newV = newGraph.vertices


for pairs in [ (0, 1), (0, 2), (1, 3), (1, 4), (2, 4), (3, 4), (3, 5), (4,5) ]:
    newGraph.addBiEdge( newV[pairs[0]], newV[pairs[1]] )

print(newGraph)

levels = BFS(newGraph.vertices[0], newGraph)
for i in range(len(levels)):
    print("Level", i, ":")
    for j in levels[i]:
        print("\t", j)



# now what's the distance from node 0 to node 6?
for i in range(len(levels)):
    if newGraph.vertices[5] in levels[i]:
        print("distance from 0 to 6 is", i)
        break


# Depth-First search
def DFS_helper(w, currentTime, verbose):
    if verbose:
        print("Time:", currentTime, ":\t entering", w)
    w.inTime = currentTime
    currentTime +=1
    w.status = "inprogress"
    
    for v in w.getOutNeighbors():
        if v.status == "unvisited":
            currentTime = DFS_helper(v, currentTime, verbose)
            currentTime +=1
            
    w.outTime = currentTime
    w.status = "done"
    
    if verbose:
        print("Time", currentTime, ":\t leaving", w)
    
    return currentTime

def DFS(w, G, verbose=False):
    for v in G.vertices:
        v.status = "unvisited"
        v.inTime = None
        v.outTime = None
    return DFS_helper( w, 0, verbose)


# Application: Topological sorting
def topoSort_helper( w, currentTime, ordering, verbose ):
    if verbose:
        print("Time", currentTime, ":\t entering", w)
    
    w.inTime = currentTime
    currentTime += 1
    w.status = "inprogress"
    
    for v in w.getOutNeighbors():
        if v.status == "unvisited":
            currentTime = topoSort_helper( v, currentTime, ordering, verbose )
            currentTime += 1
    
    w.outTime = currentTime
    w.status = "done"
    
    ordering.insert(0, w)
    
    if verbose:
        print("Time", currentTime, ":\t leaving", w)
    
    return currentTime


def topoSort(G, verbose=False):
    w = G.vertices[0]
    ordering = []
    for v in G.vertices:
        v.status = "unvisited"
        v.inTime = None
        v.outTime = None
    topoSort_helper( w, 0, ordering, verbose )
    return ordering

otherG = CustomGraph()

dpkg = CustomVertex( "dkpg" )
coreutils = CustomVertex( "coreutils" )
multiarch_support = CustomVertex( "multiarch_support" )
libselinux1 = CustomVertex("libselinux1")
libbz2 = CustomVertex( "libbz2" )
tar = CustomVertex("tar")

for N in [ dpkg, coreutils, multiarch_support, libselinux1, libbz2, tar ]:
    otherG.addVertex( N )

otherG.addDiEdge( dpkg, multiarch_support )
otherG.addDiEdge( dpkg, coreutils )
otherG.addDiEdge( dpkg, tar )
otherG.addDiEdge( dpkg, libbz2 )
otherG.addDiEdge( coreutils, libbz2 )
otherG.addDiEdge( coreutils, libselinux1 )
otherG.addDiEdge( libbz2, libselinux1 )
otherG.addDiEdge( libselinux1, multiarch_support )

print(otherG)

newV = topoSort(otherG, verbose =True)
for v in newV:
    print(v)

        