# Tech Notes

## Patterns in Microservices
1. Service Discovery - Client and Server side service discovery
2. Circuitbreaker Pattern

## Microservices Level-2 Notes

**Fault Tolerance** - Given a fault, what tolerance your system has for a fault. Degree of handling failures to minimize impact.

**Resilience** - How many faults can a system tolerate before it's brought down to it's knees. How much can a system bounce back? Is there a mechanism by which a system corrects itself (eg. after an outage).

### Circuitbreaker Pattern

**TLDR:**
1. When to break circuit?
2. What to do when circuit breaks?
3. When to resume requests?

We want to fail fast. Avoid cascading failures and stop exacerbating the problem.

1. Detect something is wrong (a service being slow or completely unresponsive)
2. Take temporary steps to avoid the situation getting worse.
3. Deactivate the "problem" component so that it doesn't affect downstream components.

Circuitbreaker's basic function is to interrupt the current flow after a fault is detected. Unlike a fuse, which operates once and then must be replaced, a circuitbreaker can be reset (either manually or automatically) to resume normal operation.

Circuitbreaker's configuration has the trigger point of when the circuitbreaker must kick in. CircuitBreaker parameters control this trigger point.

**When does the circuit trip? How to configure CircuitBreaker parameters?**
* Last n requests to consider for a decision.
* How many of those should fail? This depends on your average RPS and the size of your threadpool.
* Timeout Duration. If the timeout duration is elapsed, then CB treats this as a failure as well.

**When does the circuit un-trip/(get back to normal)?**
* How long after a circuit-trip to try again?

Circuit is tripped. But you still get requests for which you need to call the faulty service. What do you do? We need a fallback. Here are things you can do.
* Throw an error. Treat this as last resort.
* Return a "default" fallback response. Better than ^^.
* Save previous responses to cache and use that when possible. Best option.

### The Bulkhead Pattern
* Have separate threadpools for every downstream service.
* Have separate limits for max_threads for each of those threadpools.
* Hystrix configuration has two levels of wait - threads in the active threadpool waiting for a response from a faulty service AND a waiting queue for threads waiting to get a place in the active threadpool.
* For a thread that does not make it to the waiting queue, this will be circuitbroken.

## Microservices Configuration Best Practices

| Specificity | Changing | Choice |
|-------------|----------|--------|
| Microservice Specific | No | Property Files |
| Microservice Specific | Yes | Config Server |

## GIT Sign Commits

```bash
git config --global gpg.format ssh
git config --global user.signingkey ~/.ssh/id_rsa.pub
git config --global commit.gpgsign true
git config --global gpg.ssh.allowedSignersFile ~/.ssh/allowed_signers
echo "$(git config --get user.email) namespaces=\"git\" $(cat ~/.ssh/id_rsa.pub)" > ~/.ssh/allowed_signers
cat ~/.ssh/id_rsa.pub
git log --show-signature
```

## GIT Shortcuts

```bash
git config --global alias.ac "commit -am"
```

then simply use `git ac "<commit message>"`

```bash
git stash save <any reference name>
```

eg. `git stash cool_stuff`

```bash
git log --graph --oneline --decorate
```

Search docs on git bisect

**Squash to main:**
```bash
git rebase main --interactive
```

**When making commits:**
```bash
git commit --fixup
git commit --squash
```

Then:
```bash
git rebase -i --autosquash
git clean -df // to get rid of untracked files/changes
```

## Emerging Technologies

1. AI & ML.
2. Quantum Computing.
3. Blockchain.
4. Edge Computing.
5. AR and VR.

## 5 Things I wish I knew before starting a career in Tech

1. **Soft Skills** - Communication, Negotiation, Advocating for yourself.
2. **T-shaped skillsets** - Knowing a little bit about everything (breadth) v/s knowing few things very very well (depth).
3. **Take care of your body.**
4. **Act like a professional.** Commit to becoming really good at your craft. Continually upskilling.
5. **Figure out what you want to do in the long run** and drive your career towards that goal.

## Framework to learn almost anything new

1. Imitate the best until you're getting consistent results.
2. Learn to make finer and finer distinctions until you can clearly see why each one works in different situations.
3. Learn to assign higher or lower value to different mindsets, behaviors, and results.
4. Create variations on the best of those ideas, get more experience.
5. Innovate.