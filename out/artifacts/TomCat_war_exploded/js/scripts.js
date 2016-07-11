/**
 * Created by pooja on 6/12/16.
 */
function validateForm()
{
    var x=document.forms["login"]["username"].value;
    if (x==null || x=="")
    {
        alert("Please fill out the username");
        return false;
    }
}