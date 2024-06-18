<script>
	import "../app.css";
	import { Toaster } from 'svelte-french-toast';

	import Detached from './home/components/Detached.svelte';
</script>

<Toaster />

<Detached>
	<div class="p-4 flex flex-col gap-2 bg-base-200 rounded-xl ">
		<span class="font-bold text-center">Temp test</span>
		<a href="/login" class="btn btn-primary btn-sm">Go to Login</a>
		<a href="/register" class="btn btn-primary btn-sm">Go to Register</a>
		<a href="/home" class="btn btn-primary btn-sm">Go to Home</a>
	</div>
</Detached>

<slot/>
