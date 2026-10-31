# Test Design — Signup / Registration Form

Manual test design document for the signup flow of
[automationexercise.com](https://www.automationexercise.com/login).

This file exists to show the reasoning behind the test cases, not just the
test cases themselves: which inputs are worth testing, which ones are
redundant, and why each case is in the suite.

| Item | Value |
|---|---|
| Author | Omar Monir |
| Version | 1.0 |
| Date | 2026-10-31 |
| Screens under test | `/login` (New User Signup! form) and `/signup` (Enter Account Information form) |
| Browser used for design | Chrome / Brave, Windows 11 |
| Out of scope | Login with existing account, cart, checkout, API (covered by the separate RestAssured repository) |

---

## 1. What the page markup already tells us

Read directly from the HTML delivered by `/login` on 2026-10-31. This matters,
because it decides which tests are worth writing.

| Field | Markup | Consequence for testing |
|---|---|---|
| Signup Name | `<input type="text" name="name" required data-qa="signup-name">` | Empty submit is blocked by the HTML5 `required` rule in the browser, never reaching the server |
| Signup Email | `<input type="email" name="email" required data-qa="signup-email">` | The browser also rejects malformed addresses before submission (`type="email"`), so a format test must bypass or observe client-side validation |
| Form action | `POST /signup` | Server-side validation can be tested independently of the browser by sending the same POST directly |

**The important conclusion:** a script that clicks Signup on an empty form is
testing Chrome's form validation, not the application. Real coverage starts
where the browser stops helping — duplicate emails, unusual but valid text,
length extremes, and dates that do not exist.

---

## 2. Techniques used

**Equivalence partitioning** — split every input into groups that the system
should treat the same way, then test one value per group. For a name field:
empty, normal, very long, special characters, whitespace-only. Six groups, six
tests, instead of forty near-identical names.

**Boundary value analysis** — bugs concentrate at the edges of a range. If a
field accepts 1 to 50 characters, the interesting values are 0, 1, 2, 49, 50
and 51. Value 25 is tested only once, as a control.

**Decision table** — for inputs that interact. Name × Email × Password gives
combinations the single-field tests cannot reach (for example: valid name with
a duplicate email must be rejected, not accepted because the name was fine).

**State transition** — the flow only makes sense as a sequence: form →
account created → confirmation → logout → login with the same credentials.
A registration "pass" that cannot log in later is meaningless.

---

## 3. Test data

| Data | Value | Why |
|---|---|---|
| Valid new email | `omar.td.<timestamp>@example.com` | Timestamp keeps every run unique, so a re-run never hits "already exists" |
| Existing email | `software.test@example.com` | Known registered account on the public site |
| Valid password | `Test@12345` | Mixed case, digit, symbol |
| Country | `India` | First option in the dropdown |
| Mobile | `01000000000` | Placeholder in Egyptian format — deliberately not a real subscriber number |

---

## 4. Section A — New User Signup form (`/login`), 12 cases

| ID | Title | Input | Expected result | Technique | Status |
|---|---|---|---|---|---|
| TD-SU-01 | Signup with all fields empty | Name = empty, Email = empty | Submit is blocked by the browser; Name field marked invalid; no request reaches the server | Equivalence (empty) | [verify] |
| TD-SU-02 | Signup with valid new data | Name = `Omar Test`, unique email | Opens "Enter Account Information" page | Equivalence (valid) | Verified — automated TestCase1 |
| TD-SU-03 | Signup with an already registered email | Name = `Omar Test`, Email = `software.test@example.com` | Error shown: "Email Address already exist!" and no account data page is shown | Equivalence (duplicate) | Verified — automated TestCase5 |
| TD-SU-04 | Signup with name only, email empty | Name = `Omar Test`, Email = empty | Submit blocked; the email field is the one flagged invalid | Equivalence (partial) | [verify] |
| TD-SU-05 | Signup with email only, name empty | Name = empty, Email = unique | Submit blocked; the name field flagged invalid | Equivalence (partial) | [verify] |
| TD-SU-06 | Email without `@` | `omartest.example.com` | Browser rejects the format; if the request is forced, the server must also reject it | Equivalence (malformed) | [verify] |
| TD-SU-07 | Email without a domain | `omar@` | Rejected by browser and server | Equivalence (malformed) | [verify] |
| TD-SU-08 | Email with spaces | `omar test@example.com` | Rejected — a space is not legal in the local part | Equivalence (malformed) | [verify] |
| TD-SU-09 | Email in uppercase | `OMAR.TD.<timestamp>@EXAMPLE.COM` | Treated as the same address as lowercase; if a duplicate in different case is accepted, that is a defect (case-sensitive uniqueness) | Equivalence (case) | [verify] |
| TD-SU-10 | Name with leading and trailing spaces | `   Omar Test   ` | Accepted; stored value must be trimmed (check the confirmation page and later the account details page) | Equivalence (whitespace) | [verify] |
| TD-SU-11 | Name with special characters | `O$mar <Test> ' "` | Accepted and displayed literally — no HTML injection, no truncation, no 500 error | Equivalence (special) | [verify] |
| TD-SU-12 | Name at length extremes | 1 char / 100 chars / 1000 chars | 1 and 100 accepted, 1000 either rejected with a clear message or truncated consistently; a server error is a defect | Boundary | [verify] |

---

## 5. Section B — Enter Account Information form (`/signup`), 18 cases

Reached only after a successful step in Section A.

| ID | Title | Input | Expected result | Technique | Status |
|---|---|---|---|---|---|
| TD-SU-13 | Complete registration with minimum required data | Title, Name, Password, Date of birth, First name, Last name, Address, Country, State, City, Zip, Mobile filled | "Account Created!" page shown; user can log in with the same credentials afterwards | State transition | [verify] |
| TD-SU-14 | Title not selected | Everything valid except Title | Registration blocked or Title defaults to a value — behaviour must be consistent | Equivalence | [verify] |
| TD-SU-15 | Password empty | Password = empty | Blocked; password field flagged invalid | Equivalence (empty) | [verify] |
| TD-SU-16 | Password at length extremes | 1 char / 500 chars | 1 char accepted or rejected by an explicit rule, never silently; 500 either rejected with a message or hashed and accepted | Boundary | [verify] |
| TD-SU-17 | Password with only spaces | `      ` | Rejected, or accepted as a literal password — must not be treated as empty while logged in as whitespace | Equivalence | [verify] |
| TD-SU-18 | Date of birth day boundaries | Day = 1 and Day = 31 | Accepted for a month with 31 days | Boundary | [verify] |
| TD-SU-19 | Impossible date — 31 February | Month = February, Day = 31 | Rejected with a validation message; if accepted, that is a defect (invalid date stored) | Boundary / decision table | [verify] |
| TD-SU-20 | Date of birth in the future | Year = 2026, current month | Rejected, or accepted because the site does not validate age — behaviour must be recorded | Boundary | [verify] |
| TD-SU-21 | Date of birth at the earliest selectable year | Lowest year in the dropdown | Accepted (a very old user is still a valid user) | Boundary | [verify] |
| TD-SU-22 | Newsletter and offers checkboxes unchecked | Both unchecked | Registration succeeds; user is not subscribed | Equivalence | [verify] |
| TD-SU-23 | Newsletter and offers checkboxes checked | Both checked | Registration succeeds; subscription state recorded (verify on the account page or by email) | Equivalence | [verify] |
| TD-SU-24 | First name and last name at length extremes | 1 char / 200 chars | Accepted at 1; at 200 either rejected with a message or truncated consistently — no 500 error | Boundary | [verify] |
| TD-SU-25 | Address field with a very long value | 500 characters | Rejected with a message or truncated; layout must not break on the confirmation page | Boundary | [verify] |
| TD-SU-26 | Country not selected | Country = default empty option | Blocked, or a default country saved — must be consistent and documented | Equivalence | [verify] |
| TD-SU-27 | All dropdown options selectable | Each Country option | Every option can be saved and is displayed correctly on the confirmation page | Equivalence (partition per option) | [verify] |
| TD-SU-28 | Zip code with letters | `ABCDE` | Accepted (Egyptian and UK postcodes contain letters) | Equivalence | [verify] |
| TD-SU-29 | Mobile number with letters or symbols | `abc@@@` | Rejected with a validation message — most likely defect area, since the field has no format constraint in the markup | Equivalence | [verify] |
| TD-SU-30 | Mobile number length extremes | 1 digit / 30 digits | Rejected outside a sensible range, or accepted consistently | Boundary | [verify] |

---

## 6. Section C — Submission, UI and non-functional, 8 cases

| ID | Title | Action | Expected result | Technique | Status |
|---|---|---|---|---|---|
| TD-SU-31 | Double-click Create Account | Click the button twice quickly | Exactly one account created; the second click does not produce a duplicate-account error page or a server error | Idempotency | [verify] |
| TD-SU-32 | Browser Back after account creation | Create the account, press Back | No resubmission prompt that creates a second account; user is not left on a broken page | State transition | [verify] |
| TD-SU-33 | Refresh the account information page | Press F5 on `/signup` | Page reloads without losing the session or duplicating data | State transition | [verify] |
| TD-SU-34 | Direct URL access without a signup step | Open `/signup` in a fresh session | Redirected to `/login` — the form must not be reachable without the first step | Access control | Verified — returns the login page |
| TD-SU-35 | Tab order and keyboard-only completion | Fill the whole form using only Tab and Enter | Focus moves in visual order; the form can be submitted by keyboard | Accessibility | [verify] |
| TD-SU-36 | Labels, placeholders and error text | Inspect each field and each error | Every field is identifiable without relying on placeholder text only; errors are next to the field they describe | Usability | [verify] |
| TD-SU-37 | Layout at narrow viewport | Resize to 375 px width | Fields stay inside the container, no overlap, button reachable | Responsive | [verify] |
| TD-SU-38 | Session persistence after registration | Register, close the browser, reopen the site | User is logged in, or can log in with the created credentials — the account really exists in the database | State transition | [verify] |

---

## 7. Boundary values used

| Field | Values tested | Reason |
|---|---|---|
| Name | empty, 1, 100, 1000 characters | Above/below any plausible column limit |
| Password | empty, 1, 500 characters, spaces-only | Short inputs are the classic weak-validation gap |
| Day | 1, 31, 32 (not offered by the dropdown — checked via a forced request) | Day range edges and a value outside the list |
| Year | lowest option, current year | Age validation boundaries |
| Mobile | 1, 30 digits, letters | Numeric field without an obvious format rule |
| Address | 500 characters | Layout and storage limit |

Only the edges are tested. A value in the middle of the range (30, 50
characters) is covered once, as a control, because it can only fail if the
edges already fail.

---

## 8. Decision table — what must be rejected

| Name valid | Email valid | Email unused | Password valid | Expected outcome |
|---|---|---|---|---|
| No | Yes | Yes | Yes | Blocked — name required |
| Yes | No | Yes | Yes | Blocked — malformed email |
| Yes | Yes | No | Yes | Blocked — "Email Address already exist!" |
| Yes | Yes | Yes | No | Blocked — password required |
| Yes | Yes | Yes | Yes | Account created, confirmation page shown |

Only the last row is the happy path. Every other row is a rule the system must
enforce, and each is one test case.

---

## 9. Traceability to the automated suite

| Manual cases | Automated test | Note |
|---|---|---|
| TD-SU-02 | `TestCase1` | Full registration with a timestamped email |
| TD-SU-03 | `TestCase5` | Uses the fixed existing account `software.test@example.com` |
| TD-SU-13, TD-SU-38 | partially covered by `TestCase2` | Logs in after registration |
| TD-SU-34 | covered by the page-level redirect check | Verified manually |
| All others | not automated | They are validation-boundary cases; automating them would mean bypassing the browser, which is a deliberate scope decision, not a gap |

---

## 10. Execution log

Fill this in when the cases are run. Empty means designed but not executed.

| Date | Case ID | Browser | Result (Pass / Fail) | Defect ID | Note |
|---|---|---|---|---|---|
| | | | | | |

---

## 11. What this document deliberately does not do

- It does not claim these cases were executed. Rows marked `[verify]` are
  designed and waiting for a run.
- It does not invent validation messages. Messages quoted here are the two that
  were actually observed on the site ("Email Address already exist!",
  "Account Created!").
- It does not duplicate the automated suite. Automated tests prove the happy
  path works repeatedly; this document proves the failure paths were thought
  through.
