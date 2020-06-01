
public class avl{

    public static void main(String[] args){
        solve();
    }
    public static class Node{
        int data=0;
        Node left = null;
        Node right = null;
        int height = 0;
        int bal = 0;


        Node(int data){
            this.data = data;
            this.height = 0;
            this. bal = 0;
        }

        Node(){

        }
    
    }

    
    public static Node constructbst(int[]arr,int si, int ei){
        if(si>ei) return null;
        int mid = (si+ei)>>1;

        Node node = new Node(arr[mid]);
        node.left = constructbst(arr, si, mid-1);
        node.right = constructbst(arr, mid+1, ei);

        updateHeightAndBal(node);
        return node;
    }

    public static void display(Node node){
        if(node==null) return;
        
        String str = " ";

        str += (node.left != null) ? node.left.data + "[" + (node.left.bal) + "," + (node.left.height) + "]"  : ".";
        str += "<-" + node.data + "[" + (node.bal) + "," + (node.height) + "]" + "->"; 
        str += (node.right != null) ? node.right.data + "[" + (node.right.bal) + "," + (node.right.height) + "]" : ".";

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static void updateHeightAndBal(Node node){
        int lh = 0;
        int rh = 0;

        if(node.left==null) lh = -1;
        if(node.right==null) rh = -1;

        if(node.left!=null){
            lh = node.left.height;
        }
        if(node.right!=null){
            rh = node.right.height;
        }

        node.height = Math.max(lh,rh)+1;
        node.bal = lh-rh;
    }

    // ll rotation 
    public static Node ll(Node A){
        Node B = A.left;
        Node Bkaright = B.right;

        B.right = A;
        A.left = Bkaright;
         
        updateHeightAndBal(A);
        updateHeightAndBal(B);

        return B;
    }

    //rr rotation
    public static Node rr(Node A){
        Node B = A.right;
        Node Bkaleft = B.left;

        B.left = A;
        Bkaleft = A.right;

        updateHeightAndBal(A);
        updateHeightAndBal(B);

        return B;
    }

    public static Node getrotation(Node node){
        updateHeightAndBal(node);
        if(node.bal==2){ // ll, lr
            if(node.left.bal==-1){ //lr
                node.left = rr(node.left);
                return ll(node);
            }
            else{ //ll
                return ll(node);
            }
        }
        else if(node.bal==-2){ //rr, rl
            if(node.right.bal==1){ //rl
                node.right = ll(node.right);
                return rr(node);
            }
            else{ //rr
                rr(node);
            }
        }

        return node;
    }

    public static Node addNode(Node node, int data){
        if(node==null){
            Node newnode = new Node(data);
            return newnode;
        }

        if(data < node.data){
            node.left = addNode(node.left, data);
        }
        else{
            node.right = addNode(node.right, data);
        }

        return getrotation(node);
    }

    public static int maximumEle(Node node){
        Node curr = node;
        while(curr.right!=null){
            curr = curr.right;
        }
        return curr.data;
    } 

    public static Node removeData(Node root, int data){
        if(root==null){
            return null;
        }
        if(data<root.data){
            root.left = removeData(root.left, data);
        }
        else if(data>root.data){
            root.right = removeData(root.right, data);
        }
        else{  // data found.
      
            if(root.left == null || root.right == null) 
            return root.left!=null?root.left:root.right;
            
            int maxInleft=maximumEle(root.left);
            root.data=maxInleft;
            root.left=removeData(root.left,maxInleft);
         
        }
        return getrotation(root);
    }



    public static void solve(){
        int[] arr = {10, 20,30,40,50,60,70,80,90,100};

        Node root = constructbst(arr, 0, arr.length-1);

        display(root);
        
    }
    
}