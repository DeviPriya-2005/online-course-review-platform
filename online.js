const API_URL = "http://localhost:8080/api";

async function loadCourses() {
  const res = await fetch(`${API_URL}/courses`);
  const courses = await res.json();
  const container = document.getElementById("courses");
  container.innerHTML = "";

  courses.forEach(c => {
    const div = document.createElement("div");
    div.className = "course";
    div.innerHTML = `
      <h3>${c.title} <span style="color:#888;">(${c.code})</span></h3>
      <p><b>Category:</b> ${c.category}</p>
      <p><b>Instructor:</b> ${c.instructor}</p>
      <button onclick="viewCourse(${c.id})">View Details</button>
    `;
    container.appendChild(div);
  });
}

async function viewCourse(id) {
  const res = await fetch(`${API_URL}/courses/${id}`);
  const data = await res.json();
  const container = document.getElementById("courses");

  container.innerHTML = `
    <div class="course">
      <h2>${data.course.title}</h2>
      <p><b>Instructor:</b> ${data.course.instructor}</p>
      <p><b>Average Rating:</b> ⭐ ${data.avgRating.toFixed(1)}</p>
      
      <h3>Reviews</h3>
      ${data.reviews.length > 0 
        ? data.reviews.map(r => `<div class="review"><b>${r.user}</b>: ⭐ ${r.rating} <br> ${r.comment}</div>`).join("") 
        : "<p>No reviews yet.</p>"}

      <h3>Add/Edit Review</h3>
      <input id="user" placeholder="Your Name">
      <input id="rating" type="number" min="1" max="5" placeholder="Rating (1-5)">
      <textarea id="comment" placeholder="Write your comment..."></textarea>
      <button onclick="submitReview(${id})">Submit</button>
      <br><br>
      <button onclick="loadCourses()">⬅ Back to Courses</button>
    </div>
  `;
}

async function submitReview(courseId) {
  const user = document.getElementById("user").value;
  const rating = document.getElementById("rating").value;
  const comment = document.getElementById("comment").value;

  if (!user || !rating || !comment) {
    alert("Please fill all fields!");
    return;
  }

  await fetch(`${API_URL}/reviews`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ course: { id: courseId }, user, rating, comment })
  });

  viewCourse(courseId);
}

loadCourses();
