<script type="text/javascript">
window.onload = function () {
    document.getElementById("password").onchange = validatePassword;
    document.getElementById("password2").onchange = validatePassword;
}
function validatePassword(){
    var pass2=document.getElementById("password2").value;
    var pass1=document.getElementById("password").value;
    if(pass1!=pass2)
        document.getElementById("password2").setCustomValidity("Passwords Don't Match");
    else
        document.getElementById("password2").setCustomValidity('');
//empty string means no validation error

}
</script>