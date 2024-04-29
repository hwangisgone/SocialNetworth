<script>
	import "../app.css";

	import Detached from './components/Detached.svelte';
</script>

<Detached>
	<div class="p-4 flex flex-col gap-2 bg-black rounded-xl ">
		<span class="font-bold text-center">Temp test</span>
		<a href="./login" class="btn btn-primary btn-sm">Go to Login</a>
		<a href="./register" class="btn btn-primary btn-sm">Go to Register</a>
		<a href="./" class="btn btn-primary btn-sm">Go to Home</a>
	</div>
</Detached>

<slot />