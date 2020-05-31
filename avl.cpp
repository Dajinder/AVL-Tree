#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    int data;
    Node *left = nullptr;  // Node* left=nullptr;
    Node *right = nullptr; // Node* right=nullptr;

    int height = 0;
    int bal = 0;

    Node(int data)
    {
        this->data = data;
        this->height = 0;
        this->bal = 0;
    }

    Node()
    {
    }
};

Node *constructAVL(vector<int> &arr, int si, int ei)
{
    if (si > ei)
        return nullptr;

    int mid = (si + ei) >> 1; // (si+ei)/2;
    Node *node = new Node(arr[mid]);
    node->left = constructAVL(arr, si, mid - 1);
    node->right = constructAVL(arr, mid + 1, ei);

    return node;
}

void display(Node *node)
{
    if (node == nullptr)
        return;

    string str = "";
    str += ((node->left != nullptr) ? to_string(node->left->data) : ".");
    str += " <- " + to_string(node->data) + " -> ";
    str += ((node->right != nullptr) ? to_string(node->right->data) : ".");
    cout << (str) << endl;

    display(node->left);
    display(node->right);
}

void updateHeightandBalance(Node *node){
    int lh = -1;
    int rh = -1;
    if(node->left!=nullptr){
        lh = node->left->height;
    }
    if(node->right!=nullptr){
        rh = node->right->height;
    }
    node->height = max(lh,rh)+1;
    node->bal = lh-rh;
}

//ll rotation
Node *ll(Node *A){
    Node *B = A->left;
    Node *Bkaright = B->right;

    B->right = A;
    A->left = Bkaright;

    updateHeightandBalance(A);
    updateHeightandBalance(B);

    return B;

}

Node *rr(Node *A){
    Node *B = A->right;
    Node *Bkaleft = B->left;
    B->left = A;
    A->right = Bkaleft;

    updateHeightandBalance(A);
    updateHeightandBalance(B);

    return B;
}

void solve()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130};
    Node *root = constructAVL(arr, 0, arr.size() - 1);
    display(root);
 
}

int main()
{
    solve();
    return 0;
}

