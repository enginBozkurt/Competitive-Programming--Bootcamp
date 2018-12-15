// 

#include "stdafx.h"
#include <iostream>

using namespace std;

struct node {
	int data;
	node *next;
};

class Linked_List {
private:
	node *head, *tail;
public :
	Linked_List() {
		head = NULL;
		tail = NULL;
	}

	void createNode(int value) {
		node *temp = new node;
		temp->data = value;
		temp->next = NULL;

		if (head == NULL) {
			head = temp;
			tail = temp;
			temp = NULL;
		}

		else {
			tail->next = temp;
			tail = temp;
		}
	}

	void displayNodes() {
		//make a temporary node and pass the address of the head node to it
		node *temp = new node;
		temp = head;

		while (temp != NULL) {
			cout << temp->data << "\t";
			temp = temp->next;
		}
	}

	void insert_start(int value) {
		node *temp = new node;
		temp->data = value;
		temp->next = head;
		head = temp;
	}

	void insert_position(int pos, int value) {
		node *prev = new node;
		node *curr = new node;
		node *temp = new node;

		curr = head;

		for (int i = 1; i < pos; i++) {
			prev = curr;
			curr = curr->next;
		}

		temp->data = value;
		prev->next = temp;
		temp->next = curr;
	}

	void delete_first() {
		node *temp = new node;
		temp = head;
		head = head->next;
		delete temp;
	}

	void delete_last() {
		node *prev = new node;
		node *curr = new node;
		
		curr = head;

		while (curr->next != NULL) {
			prev = curr;
			curr = curr->next;
		}

		tail = prev;
		prev->next = NULL;
		delete curr;
	}

	void delete_position(int pos) {
		node *current = new node;
		node *previous = new node;
		current = head;
		for (int i = 1; i< pos; i++)
		{
			previous = current;
			current = current->next;
		}

		previous->next = current->next;
	}

};



int main()
{
	Linked_List obj;
	obj.createNode(25);
	obj.createNode(50);
	obj.createNode(90);
	obj.createNode(40);
	cout << "\n--------------------------------------------------\n";
	cout << "---------------Displaying All nodes---------------";
	cout << "\n--------------------------------------------------\n";
	obj.displayNodes();
	cout << "\n--------------------------------------------------\n";
	cout << "-----------------Inserting At End-----------------";
	cout << "\n--------------------------------------------------\n";
	obj.createNode(55);
	obj.displayNodes();
	cout << "\n--------------------------------------------------\n";
	cout << "----------------Inserting At Start----------------";
	cout << "\n--------------------------------------------------\n";
	obj.insert_start(50);
	obj.displayNodes();
	cout << "\n--------------------------------------------------\n";
	cout << "-------------Inserting At Particular--------------";
	cout << "\n--------------------------------------------------\n";
	obj.insert_position(5, 60);
	obj.displayNodes();
	cout << "\n--------------------------------------------------\n";
	cout << "----------------Deleting At Start-----------------";
	cout << "\n--------------------------------------------------\n";
	obj.delete_first();
	obj.displayNodes();
	cout << "\n--------------------------------------------------\n";
	cout << "-----------------Deleting At End-------------------";
	cout << "\n--------------------------------------------------\n";
	obj.delete_last();
	obj.displayNodes();
	cout << "\n--------------------------------------------------\n";
	cout << "--------------Deleting At Particular--------------";
	cout << "\n--------------------------------------------------\n";
	obj.delete_position(4);
	obj.displayNodes();
	cout << "\n--------------------------------------------------\n";
	system("pause");
	
    return 0;
}