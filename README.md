# Introduction

This is the first of a 3-step technical interview process:

1. Take home challenge. Implement the requirements for an imaginary billing system.
2. Live coding challenge. Extend the take home solution with additional features, in a live setting.
3. System design and architecture. Open-ended design of a production grade, highly available billing system.

The interview process mimics some of real-world problems we're facing, and allows you to tackle it using a real-world
approach.

## What we are looking for throughout this process

**Self-explanatory simple code:** We like clean, simple code. The solution you produce should speak for itself -
multiple paragraphs explaining the solution may be a sign that it isn’t straightforward enough. There's no need to
try and make the solution seem "clever". Try to balance code simplicity with relative efficiency.

**Clear separation of concerns:** Even in such simple exercise, we'd like to see you clearly separating concerns,
following the principle of single responsibility, and structuring your code accordingly.

**Test Coverage and TDD:** The solution should be developed “test-first”, and should have appropriate automated
test coverage.

**Familiarity with Java/Kotlin, including concurrency:** Even in a simple task, we want to see evidence that you
understand modern Java/Kotlin. Pay attention to data types you use in the task and any concurrency requirements
that may come up. This will be particularly important during live pair programming so if you're feeling rusty
in this area, it may be a good idea to review this topic beforehand.

**Ability to explain your thought process, decisions, and any trade-offs:** Working with colleagues is one of the
most important things you'll do. We want to see good collaboration skills, whether that's pair programming
or explaining your architecture decisions to others.

## Hints

* We're providing you with skeleton projects for Java and Kotlin that contain basic glue-code. You can assume that
  the code provided here is correct and if you're not familiar with Spring, you can simply follow it as an example.
  If you prefer, you can also start from scratch with your own project, e.g. using another framework (please create a
  separate subdirectory if you do this).
* You can choose to use either Java or Kotlin
* We will not be asking you any in-depth Spring questions.
* There are no intentional "gotchas"; if the tasks appear to be simple, that's good!
* Googling and looking up documentation is generally allowed throughout the interview process.

# Take home challenge

We bill our customers on a weekly basis. Each bill charges for transactions that occurred during a specific
**billing period**, which is identified by a **year** and a **period number**, as follows:

- Billing periods are numbered consecutively within a year.
- The first billing period starts on January 1st with period number 1.
- Each **Saturday** marks the beginning of a new billing period.
- Each **1st of a month** marks the beginning of a new billing period.

You can refer to [sample calendar for 2 months of 2019](calendar.md) for a visual representation of the above.

Please write code delivering the requirements for the two features below. This task is designed to be a straightforward
coding exercise, and it should take you no more than 2-3h.

## Non-functional requirements

- Follow a TDD approach, and ensure the cycle is clear from git commits
- Submit the solution as a PR in the given repo, and let your recruiter know that it's ready for review
- Feature 1 and 2 must be able to handle being called frequently, with multiple threads

## Feature 1

It should be possible to get a billing period for a given (local) date. The service should return the respective
billing period with periodId. This can be implemented as an internal service (no need to expose via REST). It's up to
you to decide any relevant data structures.

### Examples

| Input         | Response          |
|---------------|-------------------|
| 24th Jan 2019 | periodId = 2019-4 |
| 2nd Feb 2019  | periodId = 2019-7 |

## Feature 2

We need to expose a REST API which will return all the periods within a year, with their respective start and end dates
(inclusive).

As an example, for 2019 we should get the following periods in the response (please design an appropriate structure for
the response):

```
periodId=2019-1, from=2019-01-01, to=2019-01-04
periodId=2019-2, from=2019-01-05, to=2019-01-11
periodId=2019-3, from=2019-01-12, to=2019-01-18
periodId=2019-4, from=2019-01-19, to=2019-01-25
periodId=2019-5, from=2019-01-26, to=2019-01-31
periodId=2019-6, from=2019-02-01, to=2019-02-01
periodId=2019-7, from=2019-02-02, to=2019-02-08
periodId=2019-8, from=2019-02-09, to=2019-02-15
periodId=2019-9, from=2019-02-16, to=2019-02-22
periodId=2019-10, from=2019-02-23, to=2019-02-28
periodId=2019-11, from=2019-03-01, to=2019-03-01
periodId=2019-12, from=2019-03-02, to=2019-03-08
periodId=2019-13, from=2019-03-09, to=2019-03-15
periodId=2019-14, from=2019-03-16, to=2019-03-22
periodId=2019-15, from=2019-03-23, to=2019-03-29
periodId=2019-16, from=2019-03-30, to=2019-03-31
periodId=2019-17, from=2019-04-01, to=2019-04-05
periodId=2019-18, from=2019-04-06, to=2019-04-12
periodId=2019-19, from=2019-04-13, to=2019-04-19
periodId=2019-20, from=2019-04-20, to=2019-04-26
periodId=2019-21, from=2019-04-27, to=2019-04-30
periodId=2019-22, from=2019-05-01, to=2019-05-03
periodId=2019-23, from=2019-05-04, to=2019-05-10
periodId=2019-24, from=2019-05-11, to=2019-05-17
periodId=2019-25, from=2019-05-18, to=2019-05-24
periodId=2019-26, from=2019-05-25, to=2019-05-31
periodId=2019-27, from=2019-06-01, to=2019-06-07
periodId=2019-28, from=2019-06-08, to=2019-06-14
periodId=2019-29, from=2019-06-15, to=2019-06-21
periodId=2019-30, from=2019-06-22, to=2019-06-28
periodId=2019-31, from=2019-06-29, to=2019-06-30
periodId=2019-32, from=2019-07-01, to=2019-07-05
periodId=2019-33, from=2019-07-06, to=2019-07-12
periodId=2019-34, from=2019-07-13, to=2019-07-19
periodId=2019-35, from=2019-07-20, to=2019-07-26
periodId=2019-36, from=2019-07-27, to=2019-07-31
periodId=2019-37, from=2019-08-01, to=2019-08-02
periodId=2019-38, from=2019-08-03, to=2019-08-09
periodId=2019-39, from=2019-08-10, to=2019-08-16
periodId=2019-40, from=2019-08-17, to=2019-08-23
periodId=2019-41, from=2019-08-24, to=2019-08-30
periodId=2019-42, from=2019-08-31, to=2019-08-31
periodId=2019-43, from=2019-09-01, to=2019-09-06
periodId=2019-44, from=2019-09-07, to=2019-09-13
periodId=2019-45, from=2019-09-14, to=2019-09-20
periodId=2019-46, from=2019-09-21, to=2019-09-27
periodId=2019-47, from=2019-09-28, to=2019-09-30
periodId=2019-48, from=2019-10-01, to=2019-10-04
periodId=2019-49, from=2019-10-05, to=2019-10-11
periodId=2019-50, from=2019-10-12, to=2019-10-18
periodId=2019-51, from=2019-10-19, to=2019-10-25
periodId=2019-52, from=2019-10-26, to=2019-10-31
periodId=2019-53, from=2019-11-01, to=2019-11-01
periodId=2019-54, from=2019-11-02, to=2019-11-08
periodId=2019-55, from=2019-11-09, to=2019-11-15
periodId=2019-56, from=2019-11-16, to=2019-11-22
periodId=2019-57, from=2019-11-23, to=2019-11-29
periodId=2019-58, from=2019-11-30, to=2019-11-30
periodId=2019-59, from=2019-12-01, to=2019-12-06
periodId=2019-60, from=2019-12-07, to=2019-12-13
periodId=2019-61, from=2019-12-14, to=2019-12-20
periodId=2019-62, from=2019-12-21, to=2019-12-27
periodId=2019-63, from=2019-12-28, to=2019-12-31
```
