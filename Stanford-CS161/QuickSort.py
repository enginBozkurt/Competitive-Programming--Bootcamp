# -*- coding: utf-8 -*-
"""
Created on Mon Apr  8 17:12:54 2019

@author: Engin
"""
import numpy as np


#swap A[i] with A[j]
def swap(A, i, j):
    tmp = A[i];
    A[i] = A[j];
    A[j] = tmp

# Here is a different in-place Partition algorithm.  This was the original one introduced by Hoare.
def partition(A, start, end, p):
    #first swap to put the pivot at the end
    x = A[p]
    swap(A, p, end-1)
    # walk pointers up and down the array in opposite directions, switching things until they are in the right place.
    leftP = start
    rightP = end-1
    
    while True:
        while leftP < end-1 and A[leftP] <= x:
            leftP +=1
        while rightP > start and A[rightP] >= x:
            rightP -=1
        if rightP <= leftP:
            break;
        
        #now switch them
        swap(A, leftP, rightP)
    
    # now put the pivot value in the right place and return that location
    swap(A, end-1, leftP)
    return leftP

def quickSortHelper(A, start, end, partition):
    if end- start <= 1:
        return
    
    #choose random pivot 
    p = np.random.choice(range(start, end))
    # partition around the pivot:
    pivotLocation = partition( A, start, end, p)
    # recurse:
    quickSortHelper(A,start, pivotLocation ,partition)
    quickSortHelper(A,pivotLocation + 1, end, partition)
    
    return


def quickSort(A, partition):
    return quickSortHelper(A, 0, len(A), partition)


A = [5,4,2,3,1,1,2,3,4,5,234]

quickSort(A, partition)

print(A)